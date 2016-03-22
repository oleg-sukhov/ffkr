package ua.vn.ffkr.article.crawler.fetch;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import ua.vn.ffkr.article.crawler.model.BufferedImageWrapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static java.nio.charset.Charset.forName;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public class FetcherImpl implements Fetcher {
    private final Logger logger = LoggerFactory.getLogger(FetcherImpl.class);

    /**
     * The method retrieves html content by given url
     *
     * @param url - representation of url like http://google.com
     * @return - text representation of html page, retrieved by given url
     */
    public Optional<String> fetchPage(String url) {
        StopWatch timer = new StopWatch();
        timer.start();
        String data = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            data = httpClient.execute(httpGet, this::processHtmlPageResponse);

        } catch (IOException e) {
            logger.warn("Cannot load data from url <{}>. Cause - {}", url, getStackTrace(e));
        }
        timer.stop();
        logger.info("Fetching data from url <{}> takes {} ms.", url, timer.getTotalTimeMillis());
        return ofNullable(data);
    }

    @Override
    public Optional<BufferedImageWrapper> loadImage(String urlRepresentation) {
        BufferedImage image = null;
        StopWatch timer = new StopWatch();

        try {
            timer.start();
            URL url = new URL(urlRepresentation);
            image = ImageIO.read(url);
            timer.stop();
        } catch (IOException e) {
            String message = "Cannot load image by url {} due to the following reason: {}";
            logger.warn(message, urlRepresentation, ExceptionUtils.getStackTrace(e));
        }

        logger.info("Loading image by url {} takes {} ms.", urlRepresentation, timer.getTotalTimeMillis());

        String imageName = FilenameUtils.getName(urlRepresentation);
        return of(new BufferedImageWrapper(imageName, image));
    }

    private String processHtmlPageResponse(HttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity(), forName("UTF-8"));
    }
}
