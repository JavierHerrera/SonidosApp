package myapps.jherrera.sonidosapp;

public class Sonido {

    private String nombre;
    private String iconoPath;
    private String audioPath;

    public Sonido(String nombre, String iconoPath, String audioPath) {
        this.nombre = nombre;
        this.iconoPath = iconoPath;
        this.audioPath = audioPath;
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
}
