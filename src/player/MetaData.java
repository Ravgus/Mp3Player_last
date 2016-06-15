package player;

import javafx.beans.InvalidationListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import static player.MainWExController.mediaPlayer;
import static player.MainWExController.media;

/**
 * Created by Alex on 15.06.2016.
 */
public class MetaData {

    @FXML
    Button btn_ok;

    @FXML
    Label label_track;

    @FXML
    Label label_artist;

    @FXML
    Label label_year;

    @FXML
    Label label_album;

    @FXML
    ImageView img;

    @FXML
    public void initialize(){

        try {

            loadData();

        }

        catch (Exception e1){
            System.out.println(e1);
        }

    }

    Tooltip tooltip[] = new Tooltip[4];

    public void loadData(){

        try{

            for(int i=0; i<4; i++){
                tooltip[i] = new Tooltip();
            }

            try {
                label_track.setText("Трэк: " + media.getMetadata().get("title").toString());
                tooltip[0].setText(media.getMetadata().get("title").toString());
            }
            catch (Exception e){
                System.out.println("No Track");
            }

            try {
                label_album.setText("Альбом: " + media.getMetadata().get("album").toString());
                tooltip[1].setText(media.getMetadata().get("album").toString());
            }
            catch (Exception e){
                System.out.println("No Album");
            }

            try {
                label_artist.setText("Исполнитель: " + media.getMetadata().get("artist").toString());
                tooltip[2].setText(media.getMetadata().get("artist").toString());
            }
            catch (Exception e){
                System.out.println("No Artist");
            }

            try {
                label_year.setText("Год: " + media.getMetadata().get("year").toString());
                tooltip[3].setText(media.getMetadata().get("year").toString());
            }
            catch (Exception e){
                System.out.println("No Year");
            }

            try {
                if(media.getMetadata().get("image") != null) {
                    img.setImage((Image) media.getMetadata().get("image"));
                }

                else {
                    System.out.println("Image == null");
                }
            }
            catch (Exception e){
                System.out.println("No Image");
            }

            label_artist.setTooltip(tooltip[2]);
            label_year.setTooltip(tooltip[3]);
            label_album.setTooltip(tooltip[1]);
            label_track.setTooltip(tooltip[0]);

        }

        catch (Exception e){
            System.out.println(e);
        }

    }

    public void ActionExit(){

        Stage stage = (Stage) btn_ok.getScene().getWindow();
        stage.hide();
        MainWExController.metaData = false;

    }

}
