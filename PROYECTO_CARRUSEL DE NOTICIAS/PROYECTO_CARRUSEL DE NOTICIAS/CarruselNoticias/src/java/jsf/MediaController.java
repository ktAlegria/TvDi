/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author GUSTAVO_AL
 */
@ManagedBean(name = "mediaController1")
@SessionScoped
public class MediaController {

    private StreamedContent media;

    /** Creates a new instance of MediaController */
    public MediaController() {
        try {
            InputStream stream = this.getClass().getResourceAsStream("http://localhost:32949/CarruselNoticias/noticia.flv");
            System.out.println("stream " + stream);
            media = new DefaultStreamedContent(stream, "video/quicktime");

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public StreamedContent getMedia() {
        return media;
    }
}
