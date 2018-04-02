package myapps.jherrera.sonidosapp.objects;

public class Sonido {

    private String nombre;
    private String iconoPath;
    private String audioPath;

    public static final String BASE_URL = "https://sonidos-test.herokuapp.com/";
    private final String IMAGES_PATH = "static/images/";
    private final String AUDIO_PATH = "static/audio/";

    public Sonido(String nombre, String iconoPath, String audioPath) {
        this.nombre = nombre.replaceAll("\"", "");;
        this.iconoPath = createImagePath(iconoPath);
        this.audioPath = createAudioPath(audioPath);
    }

    public String getNombre() {
        return nombre;
    }

    public String getIconoPath() {
        return iconoPath;
    }

    public String getAudioPath() {
        return audioPath;
    }

    private String createImagePath(String path){

        String realPath = BASE_URL + IMAGES_PATH  + path.substring(path.lastIndexOf("\\") + 1);
        realPath = realPath.replaceAll("\"", "");

        return realPath;
    }

    private String createAudioPath(String path){

        String realPath = BASE_URL + AUDIO_PATH  + path.substring(path.lastIndexOf("\\") + 1);
        realPath = realPath.replaceAll("\"", "");

        return realPath;
    }
}
