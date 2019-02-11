package chemin.matthieu.buildsrc

import com.google.gson.stream.JsonReader
import java.io.*

class ImportLocationJsonToProject {

    companion object {
        fun perform() {
            val userDir = System.getProperty("user.dir")
            System.out.println(userDir)

            val sourceFile = File(userDir, "/buildSrc/src/main/assets/locations.json")
            val destinationFile = File(userDir, "/app/src/main/assets/locations.json")
            System.out.println(sourceFile.absolutePath)
            System.out.println(destinationFile.absolutePath)

            sourceFile.reader().use { inputStreamReader ->
                JsonReader(inputStreamReader).use { jsonReader ->
                    FileOutputStream(destinationFile).use { outputStream ->
                        OutputStreamWriter(outputStream).use { outputStreamWriter ->
                            LocationJsonReader(outputStreamWriter).readLocationArray(jsonReader)
                            outputStreamWriter.close()
                        }
                        outputStream.close()
                    }
                    jsonReader.close()
                }
                inputStreamReader.close()
            }

        }
    }

}