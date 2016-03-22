package ua.vn.ffkr.article.crawler.handle;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import ua.vn.ffkr.article.crawler.fetch.Fetcher;
import ua.vn.ffkr.article.crawler.model.BufferedImageWrapper;
import ua.vn.ffkr.article.persistence.model.Article;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

public class ArticleImageHandler implements Handler<Article, Article> {

    @Resource
    private Fetcher fetcher;

    @Value("${ffkr.article.image.location}")
    private String articlesImagesLocation;

    @Override
    public Article handle(Article article) {
        File directory = createArticleImagesDirectory(article);
        List<String> savedImagesUrls = article.getImages()
                .stream()
                .map(fetcher::loadImage)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(image -> saveImageToDisk(image, directory))
                .collect(toList());
        article.setImages(savedImagesUrls);
        return article;
    }

    private File createArticleImagesDirectory(Article article) {
        File dir = new File(articlesImagesLocation + File.separator + article.getId().toString());
        boolean isDirectoryCreated = dir.mkdir();
        if (isDirectoryCreated) {
            return dir;
        }

        throw new RuntimeException("Cannot create directory by location " + articlesImagesLocation);
    }

    private String saveImageToDisk(BufferedImageWrapper imageWrapper, File directory) {
        File imageOutput = new File(directory, imageWrapper.getName());
        String imageType = FilenameUtils.getExtension(imageWrapper.getName());
        boolean isImageSuccessfullySaved;
        try {
            isImageSuccessfullySaved = ImageIO.write(imageWrapper.getImage(), imageType, imageOutput);
        } catch (IOException e) {
            throw new RuntimeException("Cannot save image due to the following reason: " + getStackTrace(e));
        }

        if (isImageSuccessfullySaved) {
            return imageWrapper.getName();
        }

        throw new RuntimeException("Cannot save image ");
    }
}
