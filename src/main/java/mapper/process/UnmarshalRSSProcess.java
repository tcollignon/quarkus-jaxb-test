package mapper.process;

import mapper.codegen.eforum.Feed;
import mapper.codegen.eforum.Feed.Entry;
import object.Category;
import object.EForumNews;
import object.INews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Process d'unmarshalling.
 */
@ApplicationScoped
public class UnmarshalRSSProcess {

    private final static Logger LOG = LoggerFactory.getLogger(UnmarshalRSSProcess.class);

    private static String CODEGEN_PACKAGE = "mapper.codegen.eforum";
    private static String STATISTIC = "<p>Statistiques:";

    public Collection<INews> retrieveLastNews(int nbNews, Category category) {
        return getEForumNews(getFeed()).subList(0, nbNews);
    }

    private Feed getFeed() {
        try {
            JAXBContext jc = JAXBContext.newInstance(CODEGEN_PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            URL urlForum = new URL(("http://localhost:8080/feed.xml"));
            return (Feed) unmarshaller.unmarshal(urlForum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<INews> getEForumNews(Feed feed) {
        List<INews> eforumNews = new ArrayList<>();
        for (Entry entry : feed.getEntry()) {
            eforumNews.add(mapToEForumNews(entry));
        }
        return eforumNews;
    }

    private EForumNews mapToEForumNews(Entry entry) {
        EForumNews eForumNews = new EForumNews();
        String description = entry.getContent();
        String[] content = entry.getContent().split(STATISTIC);
        if (content.length > 0) {
            description = content[0];
        }
        eForumNews.setTitle(entry.getTitle());
        eForumNews.setDescription(description);
        eForumNews.setContent(entry.getContent());
        eForumNews.setAuthor(entry.getAuthor().getName());
        eForumNews.setUrl(entry.getLink().getHref());
        return eForumNews;
    }
}
