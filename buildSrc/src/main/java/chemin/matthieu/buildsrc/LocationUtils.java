package chemin.matthieu.buildsrc;

public class LocationUtils {

    public static void addJsonLocationsToAssets() {
        ImportLocationJsonToProject.Companion.perform();
    }
}
