package player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.EqualizerBand;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static javafx.application.Platform.exit;

/**
 * Created by Atlantis on 23.05.2016.
 */
public class MainWExController {

    @FXML
    private Button btn_upload;

    @FXML
    private Button btn_plus;

    @FXML
    private Button btn_plus1;

    @FXML
    private Button btn_playlist;

    @FXML
    private Slider slider_sound;

    @FXML
    private Button btn_mute;

    @FXML
    private Button btn_loop;

    @FXML
    private Button btn_play;

    @FXML
    private Button btn_pause;

    @FXML
    private Button btn_stop;

    @FXML
    private Button btn_rewind;

    @FXML
    private Button btn_forward;

    @FXML
    private Button btn_previous;

    @FXML
    private Button btn_delete1;

    @FXML
    private Button btn_next;

    @FXML
    private Button btn_play332;

    @FXML
    private Slider slider_player;

    @FXML
    private ProgressBar progress_bar;

    @FXML
    private Label music_name_label;

    @FXML
    private Label music_time_label;

    public static ObservableList<Music> MusicData = FXCollections.observableArrayList();

    @FXML
    private TableView<Music> table_player;

    @FXML
    private TableColumn<Music, String> column_table_name;

    @FXML
    public void initialize(){ // инициализатор для файла, запустится с загрузкой fxml файлика

        Tooltips();

        if(Change){StyleUkr = MainWExController.StyleUkr;
            media = MainWExController.media;
            mediaPlayer = MainWExController.mediaPlayer;
            MusicData = MainWExController.MusicData;
            column_table_name.setCellValueFactory(new PropertyValueFactory<Music, String>("Name"));
            table_player.setItems(MusicData);
            nameArr_key = MainWExController.nameArr_key;
            music_index = MainWExController.music_index;
            current_music_index = MainWExController.current_music_index;
            fileArr = MainWExController.fileArr;
            nameArr = MainWExController.nameArr;
            sound_play = MainWExController.sound_play;
            if(sound_play){ if(!StyleUkr) btn_mute.setStyle("-fx-background-image: url(\"btnVolDownUkrActive.png\");");
            else btn_mute.setStyle("-fx-background-image: url(\"btnVolDown.png\");");}
            loop_check = MainWExController.loop_check;
            if(loop_check) { if(!StyleUkr) btn_loop.setStyle("-fx-background-image: url(\"btnReloadUkrActive.png\");");
            else btn_loop.setStyle("-fx-background-image: url(\"btnReloadActive.png\");");}
            playing = MainWExController.playing;
            current_file = MainWExController.current_file;
            if(mediaPlayer != null) {
                time();
                next_Song();
            }
            label_name = MainWExController.label_name;
            label_time = MainWExController.label_time;
            music_name_label.setText(label_name);
            music_time_label.setText(label_time);
            trans_progress_bar = MainWExController.trans_progress_bar;
            progress_bar.setProgress(trans_progress_bar);
            Change = false;}
        else{
            StyleUkr = MainWController.StyleUkr;
        media = MainWController.media;
        mediaPlayer = MainWController.mediaPlayer;
        MusicData = MainWController.MusicData;
        column_table_name.setCellValueFactory(new PropertyValueFactory<Music, String>("Name"));
        table_player.setItems(MusicData);
        nameArr_key = MainWController.nameArr_key;
        music_index = MainWController.music_index;
        current_music_index = MainWController.current_music_index;
        fileArr = MainWController.fileArr;
        nameArr = MainWController.nameArr;
        sound_play = MainWController.sound_play;
            if(sound_play){ if(StyleUkr) btn_mute.setStyle("-fx-background-image: url(\"btnVolDownUkrActive.png\");");
            else btn_mute.setStyle("-fx-background-image: url(\"btnVolDown.png\");");}
            loop_check = MainWController.loop_check;
            if(loop_check) { if(StyleUkr) btn_loop.setStyle("-fx-background-image: url(\"btnReloadUkrActive.png\");");
            else btn_loop.setStyle("-fx-background-image: url(\"btnReloadActive.png\");");}
        playing = MainWController.playing;
        current_file = MainWController.current_file;
        if(mediaPlayer != null) {
            time();
            next_Song();
        }
        label_name = MainWController.label_name;
        label_time = MainWController.label_time;
        music_name_label.setText(label_name);
        music_time_label.setText(label_time);
        trans_progress_bar = MainWController.trans_progress_bar;
        progress_bar.setProgress(trans_progress_bar);
        }


    }


    static Media media;
    static MediaPlayer mediaPlayer;
    Media current_music;

    static File current_file;
    Stage MetaDataStage;

    static int music_index = 0;
    static int current_music_index = 0;
    static int nameArr_key;
    static int nameArr_key_old;

    int position;
    Music data;

    static boolean sound_play; // проверка на mute
    static boolean loop_check; // проверка на луп
    static boolean playing; // проверка на то играет музыка или нет
    static boolean StyleUkr; // проверка на то какой сейчас стиль
    static boolean Change;
    static boolean metaData;

    private String stop_time;
    private String current_time;
    public static String label_name = "";
    public static String label_time = "";

    private double slider_position;
    private double slider_player_position;
    private double one_per;
    private double current_per;

    public static double trans_progress_bar;

    static Map<Integer, File> fileArr = new HashMap<Integer, File>();
    static Map<Integer, String > nameArr = new HashMap<Integer, String>();

    Tooltip[] tooltip = new Tooltip[15];

    private double xOffset;
    private double yOffset;


    public void ActionPlaylistHide()
    {
        try {

            MainWController.start = true;
            Stage primaryStage2 = new Stage();
            Stage stage = (Stage) btn_playlist.getScene().getWindow();

            primaryStage2.setX(stage.getX());
            primaryStage2.setY(stage.getY());

            Parent root = FXMLLoader.load(getClass().getResource("fxml/MainW.fxml"));
            primaryStage2.setTitle("Mp3Player");
            Scene scene = new Scene(root);
            if(StyleUkr){
                scene.getStylesheets().add(0, "stylesUkr.css");
                primaryStage2.getIcons().add(new Image("logoUkr.png"));}
            else { scene.getStylesheets().add(0, "styles.css");
                primaryStage2.getIcons().add(new Image("logo.png"));}
            primaryStage2.setScene(scene);
            primaryStage2.initStyle(StageStyle.TRANSPARENT);
            primaryStage2.setResizable(false);
            primaryStage2.show();
            scene.setOnMousePressed(event -> {
                xOffset = primaryStage2.getX() - event.getScreenX();
                yOffset = primaryStage2.getY() - event.getScreenY();
            });
            scene.setOnMouseDragged(event -> {
                primaryStage2.setX(event.getScreenX() + xOffset);
                primaryStage2.setY(event.getScreenY() + yOffset);
            });


            stage.hide();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void ActionStyle()
    {
        try {
            System.out.println("IN Style Ex");
            Change = true;
            Stage primaryStage2 = new Stage();
            Stage stage = (Stage) btn_playlist.getScene().getWindow();

            primaryStage2.setX(stage.getX());
            primaryStage2.setY(stage.getY());

            Parent root = FXMLLoader.load(getClass().getResource("fxml/MainWExtended.fxml"));
            primaryStage2.setTitle("Mp3Player");
            Scene scene = new Scene(root);
            if(!StyleUkr){
                scene.getStylesheets().add(0, "stylesUkr.css");
                primaryStage2.getIcons().add(new Image("logoUkr.png"));
                StyleUkr = true;}
            else {scene.getStylesheets().add(0, "styles.css");
                primaryStage2.getIcons().add(new Image("logo.png"));
                StyleUkr = false;}
            primaryStage2.setScene(scene);
            primaryStage2.initStyle(StageStyle.TRANSPARENT);
            primaryStage2.setResizable(false);
            primaryStage2.show();
            scene.setOnMousePressed(event -> {
                xOffset = primaryStage2.getX() - event.getScreenX();
                yOffset = primaryStage2.getY() - event.getScreenY();
            });
            scene.setOnMouseDragged(event -> {
                primaryStage2.setX(event.getScreenX() + xOffset);
                primaryStage2.setY(event.getScreenY() + yOffset);
            });

            stage.hide();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void ActionExit(){
        exit();
    }

    public void ActionHide(){ //обработчик сворачивания окна

        Stage stage = (Stage) btn_playlist.getScene().getWindow();
        stage.setIconified(true);

    }

    public String MusicName(File file){
        return file.getName();
    }

    public void ActionUpload(){

        try {
            current_music = PlayList();

            if (current_music != null) {
                mediaPlayer = new MediaPlayer(current_music);
                mediaPlayer.setAutoPlay(true);
                playing = true;
                time();
                next_Song();
            }
        }
        catch (NullPointerException e1){
            System.out.println("Песня не выбрана1");
            e1.printStackTrace();
        }
    }

    public void ActionAdd(){

        try {

            if(playing) { current_music = PlayListAdd();

            /*if (current_music != null) {
                time();
            }*/

            }

            else {

                current_music = PlayList();

                if (current_music != null) {
                    mediaPlayer = new MediaPlayer(current_music);
                    mediaPlayer.setAutoPlay(true);
                    playing=true;
                    time();
                    next_Song();
                }
            }
        }
        catch (NullPointerException e1){
            System.out.println("Песня не выбрана17");
            e1.printStackTrace();
        }

    }


    public Media PlayList(){

        String chosen_file_name;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
        File chosen_file = fileChooser.showOpenDialog(btn_upload.getScene().getWindow());

        if (chosen_file != null) {
            if(mediaPlayer != null){
                ActionStop();
            }

            media = new Media(chosen_file.toURI().toString());
            chosen_file_name = MusicName(chosen_file);
            current_music_index = music_index;
            addArray(chosen_file,music_index,chosen_file_name);
            current_file = chosen_file;
            music_index++;

            return media;

        } else {
            return null;
        }

    }

    public Media PlayListAdd(){

        String chosen_file_name;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
        File chosen_file = fileChooser.showOpenDialog(btn_plus.getScene().getWindow());

        if (chosen_file != null) {

            if(mediaPlayer == null){
                media = new Media(chosen_file.toURI().toString());
            }

            chosen_file_name = MusicName(chosen_file);
            addArray(chosen_file,music_index,chosen_file_name);
            music_index++;
            return media;

        } else {
            return null;
        }

    }

    public void addArray(File file, int index, String name){

        fileArr.put(index, file);
        nameArr.put(index, name);
        MusicData.add(new Music(name));
        column_table_name.setCellValueFactory(new PropertyValueFactory<Music, String>("Name"));
        table_player.setItems(MusicData);

    }

    /*public void next_SongAfterChange(){
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override public void run() {

                try {

                    if(loop_check){} else current_music_index++;

                    media = new Media(fileArr.get(current_music_index).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    playing = true;
                    time();
                    next_SongAfterChange();
                }
                catch (NullPointerException e1){
                    System.out.println("Песни закончились2/1");
                    mediaPlayer = new MediaPlayer(media);
                }

            }
        });
    }*/

    public void next_Song(){
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                for (Map.Entry entry : fileArr.entrySet()) {
                    if (entry.getValue() == current_file) {
                        nameArr_key = (int) entry.getKey();
                        break;
                    }
                }

                try {

                    if(loop_check){} else nameArr_key++;

                    media = new Media(fileArr.get(nameArr_key).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.play();
                    playing = true;
                    time();
                    next_Song();
                }
                catch (NullPointerException e1){
                    System.out.println("Песни закончились2/2");
                    mediaPlayer = new MediaPlayer(media);
                }

            }
        });
    }

    public void ActionMute(){

        try {

            if (sound_play) {
                mediaPlayer.setMute(false);
                sound_play = false;
                if(StyleUkr) btn_mute.setStyle("-fx-background-image: url(\"btnVolUpUkr.png\");");
                else btn_mute.setStyle("-fx-background-image: url(\"btnVolUp.png\");");
            } else {
                mediaPlayer.setMute(true);
                sound_play = true;
                if(StyleUkr) btn_mute.setStyle("-fx-background-image: url(\"btnVolDownUkrActive.png\");");
                else btn_mute.setStyle("-fx-background-image: url(\"btnVolDown.png\");");
            }
        }
        catch (NullPointerException e1){
            e1.printStackTrace();
        }

    }

    public void ActionStop(){

        try {

            mediaPlayer.stop();
            playing = false;
            progress_bar.setProgress(0);

        }
        catch (NullPointerException e1){
            System.out.println("Не выбрана песня5");
        }

    }


    public void ActionPause(){

        try {
            mediaPlayer.pause();
            playing = false;
            ExitMetaData();

        }
        catch (Exception e1){
            System.out.println("Не выбрана песня6");
        }

    }


    public void ActionPlay(){

        try {

            data = table_player.getSelectionModel().getSelectedItem();
            //System.out.println(data.getName());


            if (data != null) {

                for (Map.Entry entry : nameArr.entrySet()) {
                    if (entry.getValue().equals(data.getName())) {

                        nameArr_key_old = nameArr_key;
                        nameArr_key = (int) entry.getKey();
                        if(nameArr_key_old == nameArr_key) {mediaPlayer.play(); playing = true; }
                        else {

                            File next_file = fileArr.get(nameArr_key);

                            current_file = next_file;

                            if(playing) mediaPlayer.pause();

                            media = new Media(next_file.toURI().toString());
                            mediaPlayer = new MediaPlayer(media);
                            mediaPlayer.play();

                            playing = true;
                            time();
                            next_Song();

                            break;}
                    }
                }
            } else {
                if(!playing) {mediaPlayer.play();
                    playing = true;


                }

            }
        }
        catch (NullPointerException e1){
            System.out.println("Не выбрана песня");
            e1.printStackTrace();
        }

//        }
    }



    public void ActionRewind(){

        try {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
        }

        catch (NullPointerException e1){
            System.out.println("Не выбрана песня8");
        }

    }



    public void ActionForward(){

        try {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
        }

        catch (NullPointerException e1){
            System.out.println("Не выбрана песня9");
        }

    }


    public void ActionPrevious(){

        try{

            ActionStop();

            /*if(current_music_index == 0) {}
            else  current_music_index -- ;

            File next_file = fileArr.get(current_music_index);*/

            File next_file = null;

            for (Map.Entry entry : nameArr.entrySet()) {
                if (entry.getValue().equals(ChangeStringLabel(media.getSource()))) {
                    int key = (int) entry.getKey();
                    if(key == 0) {
                        next_file = fileArr.get(key);
                        break;
                    }
                    /*if(key != 0) {key--;}
                    next_file = fileArr.get(key);*/
                    next_file = fileArr.get(key-1);
                }
            }

            current_file = next_file;
            media = new Media(next_file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            playing = true;
            time();
            //next_SongAfterChange();
            next_Song();

        }

        catch (Exception e1){
            System.out.println("Невыбра песня11");
        }

    }

    public void ActionNext(){

        try{

            ActionStop();

            /*if(current_music_index == nameArr.size() - 1) {}
            else  current_music_index ++;

            File next_file = fileArr.get(current_music_index);*/

            File next_file = null;

            for (Map.Entry entry : nameArr.entrySet()) {
                if (entry.getValue().equals(ChangeStringLabel(media.getSource()))) {
                    int key = (int) entry.getKey();
                    if(key == nameArr.size() - 1) {
                        next_file = fileArr.get(key);
                        break;
                    }
                    /*if(key != nameArr.size() - 1) {key++;}
                    next_file = fileArr.get(key++);*/
                    next_file = fileArr.get(key+1);
                }
            }

            current_file = next_file;
            media = new Media(next_file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            playing = true;
            time();
            //next_SongAfterChange();
            next_Song();

        }

        catch (Exception e1){
            System.out.println("Невыбра песня12");
        }

    }


    public void ActionLoop() {

        try {
            if (loop_check == false) {
                if(StyleUkr) btn_loop.setStyle("-fx-background-image: url(\"btnReloadUkrActive.png\");");
                else btn_loop.setStyle("-fx-background-image: url(\"btnReloadActive.png\");");
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    public void run() {
                        mediaPlayer.seek(Duration.ZERO);
                        loop_check = true;
                        //System.out.println("in1");
                    }
                });
                loop_check = true;

            } else if (loop_check == true) {
                if(StyleUkr) btn_loop.setStyle("-fx-background-image: url(\"btnReloadUkr.png\");");
                else btn_loop.setStyle("-fx-background-image: url(\"btnReload.png\");");
                next_Song();
                loop_check = false;
            }
        }

        catch (NullPointerException e1){
            System.out.println("Невыбра песня10");
        }
    }


    public void ActionChangeVolumeClicked(){

        if (slider_sound.getValue() != -1) {

            slider_position = slider_sound.getValue() / 100;
            mediaPlayer.setVolume(slider_position);

        }

    }


    public void ActionChangeVolumeDragged(){

        if (slider_sound.getValue() != -1) {

            slider_position = slider_sound.getValue() / 100;
            mediaPlayer.setVolume(slider_position);

        }

    }


    public void time() {

        mediaPlayer.currentTimeProperty().addListener((observableValue, oldDuration, newDuration) -> {

            current_time = mediaPlayer.getCurrentTime().toString();
            current_time = current_time.replace(" ms", "0");
            double current_time_d = Double.parseDouble(current_time) / 1000;
            //String current_time_s = String.format("%.2f", current_time_d);

            stop_time = mediaPlayer.getStopTime().toString();

            if(stop_time.equals("UNKNOWN")){

                stop_time = "0.00";

            }

            stop_time = stop_time.replace(" ms", "0");
            double stop_time_d = Double.parseDouble(stop_time) / 1000;
            //String stop_time_s = String.format("%.2f", stop_time_d);

            //System.out.println(nameArr_key);
            //System.out.println(media.getSource()+" 1");
            //System.out.println(fileArr.get(0)+" 3");

            //music_name_label.setText(MusicName(current_file));
            music_name_label.setText(ChangeStringLabel(media.getSource()));
            label_name = ChangeStringLabel(media.getSource());

            time_string(stop_time_d, current_time_d);

        });
    }


    public void time_string(double end_time, double curr_time){

        one_per = end_time/100;
        current_per = curr_time/one_per;
        slider_player.setValue(current_per);
        progress_bar.setProgress(current_per/100);
        trans_progress_bar = current_per/100;

        int e1 = (int) (end_time/3600);
        //System.out.println(e1);
        int e2 = (int) (end_time/60 - e1*3600);
        //System.out.println(e2);
        int e3 = (int) (end_time - e2*60 - e1*3600);
        //System.out.println(e3);

        int b1 = (int) (curr_time/3600);
        //System.out.println(b1);
        int b2 = (int) (curr_time/60 - b1*3600);
        //System.out.println(b2);
        int b3 = (int) (curr_time - b2*60 - b1*3600);
        //System.out.println(b3);

        music_time_label.setText(b1+":"+b2+":"+b3+"/"+e1+":"+e2+":"+e3);
        label_time = b1+":"+b2+":"+b3+"/"+e1+":"+e2+":"+e3;

    }


    public String ChangeStringLabel(String s){

        String s_new = s;
        int check = 1;
        char[] buf;
        int rez_1 = 0;

        while (check != -1) {

            if (rez_1 != -1) {

                buf = new char[1000];

                rez_1 = s_new.indexOf("/");

                s_new.getChars(rez_1 + 1, s.length(), buf, 0);

                s_new = new String(buf);

                s_new = s_new.replace("%20"," "); //Изменяет на пробелы

            } else {

                check = -1;

            }

        }

        s_new = s_new.trim(); //удаляет пробелы в конце и начале строки

        //System.out.println(s_new);
        return s_new;

//        s = s.replaceAll("file:/","");
//        s = s.replace("/","\\");
//
//        System.out.println(s);
//        return s;

    }


    public void ActionChangeMusicClicked(){

        try {
            slider_player_position = slider_player.getValue();
            double new_pos = slider_player_position * one_per * 1000;
            mediaPlayer.seek(Duration.millis(new_pos));
        }
        catch (NullPointerException e1){
            System.out.println("Невыбра песня13");
        }

    }


    public void ActionChangeMusicDragged(){

        try{
            slider_player_position = slider_player.getValue();
            double new_pos = slider_player_position * one_per * 1000;
            mediaPlayer.seek(Duration.millis(new_pos));
        }
        catch (NullPointerException e1){
            System.out.println("Невыбра песня14");
        }


    }

    public void btn_delete(){


        try {
            position = table_player.getFocusModel().getFocusedIndex();
            data = table_player.getSelectionModel().getSelectedItem();
            int local_key;

            /*System.out.println(fileArr);
            System.out.println(nameArr);
            System.out.println(" ");*/

            if(position == -1){
                return;
            }

            for (Map.Entry entry : nameArr.entrySet()) {
                if (entry.getValue().equals(data.getName())) {
                    nameArr_key = (int) entry.getKey();
                    /*fileArr.remove(nameArr_key);
                    nameArr.remove(nameArr_key);*/
                    break;
                }
            }

            MusicData.remove(position);

            local_key = nameArr_key;

            while(true){
                if(fileArr.get(local_key+1) == null && nameArr.get(local_key+1) == null){
                    fileArr.remove(local_key);
                    nameArr.remove(local_key);
                    break;
                }
                fileArr.put(local_key,fileArr.get(local_key+1));
                nameArr.put(local_key,nameArr.get(local_key+1));
                local_key++;
            }

            /*System.out.println(fileArr);
            System.out.println(nameArr);
            System.out.println(" ");*/

        }
        catch (NullPointerException e1){
            System.out.println("Не выбрана песня");
            e1.printStackTrace();
        }

    }


    public void Tooltips(){

        for (int i = 0; i < 15; i++){
            tooltip[i] = new Tooltip();
        }

        tooltip[0].setText("Воспроизвести");
        tooltip[1].setText("Пауза");
        tooltip[2].setText("Перемотать назад");
        tooltip[3].setText("Перемотать вперед");
        tooltip[4].setText("Стоп");
        tooltip[5].setText("Следующая песня");
        tooltip[6].setText("Предыдущая песня");
        tooltip[7].setText("Повтор");
        tooltip[8].setText("Стили");
        tooltip[9].setText("Загрузить песню");
        tooltip[10].setText("Откл./Вкл. звук");
        tooltip[11].setText("Обычный вариант");
        tooltip[12].setText("Добавить в плейлист");
        tooltip[13].setText("Удалить песню");
        tooltip[14].setText("Эквалайзер");

        btn_play.setTooltip(tooltip[0]);
        btn_playlist.setTooltip(tooltip[11]);
        btn_upload.setTooltip(tooltip[9]);
        btn_mute.setTooltip(tooltip[10]);
        btn_loop.setTooltip(tooltip[7]);
        btn_pause.setTooltip(tooltip[1]);
        btn_stop.setTooltip(tooltip[4]);
        btn_rewind.setTooltip(tooltip[2]);
        btn_forward.setTooltip(tooltip[3]);
        btn_previous.setTooltip(tooltip[6]);
        btn_next.setTooltip(tooltip[5]);
        btn_play332.setTooltip(tooltip[8]);
        btn_delete1.setTooltip(tooltip[13]);
        btn_plus.setTooltip(tooltip[14]);
        btn_plus1.setTooltip(tooltip[12]);

    }


    public void ActionEqualizer(){

        /*System.out.println(mediaPlayer.getAudioEqualizer());
        System.out.println(mediaPlayer.getAudioEqualizer().getBands());
        System.out.println(mediaPlayer.getAudioEqualizer().isEnabled());
        EqualizerBand e = new EqualizerBand();*/


    }

    public void ActionMetaData(){

        try {

            if(metaData == false) {

            /*System.out.println(media.getMetadata());
            System.out.println(media.getMetadata().get("image"));*/

                Stage primaryStage2 = new Stage();

                Stage stage = (Stage) btn_plus.getScene().getWindow();

                primaryStage2.setX(stage.getX() + 505);
                primaryStage2.setY(stage.getY());

                Parent root = FXMLLoader.load(getClass().getResource("fxml/MetaData.fxml"));
                primaryStage2.setTitle("Mp3Player");
                Scene scene = new Scene(root);

                primaryStage2.setScene(scene);
                primaryStage2.initStyle(StageStyle.TRANSPARENT);
                primaryStage2.setResizable(false);
                primaryStage2.show();
                scene.setOnMousePressed(event -> {
                    xOffset = primaryStage2.getX() - event.getScreenX();
                    yOffset = primaryStage2.getY() - event.getScreenY();
                });
                scene.setOnMouseDragged(event -> {
                    primaryStage2.setX(event.getScreenX() + xOffset);
                    primaryStage2.setY(event.getScreenY() + yOffset);
                });

                metaData = true;
                MetaDataStage = primaryStage2;

            }

        }

        catch (Exception e1){
            //e1.printStackTrace();
            System.out.println(e1);
        }


    }


    public void ExitMetaData(){

        try{

            MetaDataStage.hide();
            metaData = false;
            MetaDataStage = null;

        }

        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

    }


}
