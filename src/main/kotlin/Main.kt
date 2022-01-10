import com.google.gson.Gson
import files.Data
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

val userName = "mikemitchel1"

fun main(args: Array<String>) {

    val constant = Constant()
    val allDumpedFilesPathes: MutableList<Path> = fetchFilePaths(constant.filePAth)
    fileWorker(allDumpedFilesPathes)

}

fun fileWorker(listOfAllDumpedFilePath: MutableList<Path>) {

    listOfAllDumpedFilePath.iterator().forEach {
        var newHtmlFilePath = changeFileExtension(it)

        try {
            if (writeFirstPartHtmlFile(newHtmlFilePath) == 1) {
                readJsonDataFromFile(it, newHtmlFilePath)
            }//reed and write json
            else println("Error to write firstPartn in file")
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            writeEndPartHtmlFile(newHtmlFilePath)
        }
    }

}


fun jsonEditor(jsondata: String, htmlFilePath: String): Int { //костыль долбаный нужно переделать проверку сообщения и имени

    val data: Data = Gson().fromJson<Data>(jsondata, Data::class.java)
    val replaceMessageData = ReplaceMessageData()
    var from = data.from.print_name
    var to = data.to.print_name

    if (data.text != null && data.event == "message") {

        if (data.from.username.equals(userName)) {
            val fileWriter = FileWriter(htmlFilePath, true)
            if (from.equals(userName)) {
                fileWriter.use {
                    it.write(
                        replaceMessageData.replaceAll(
                            "right", data.text,
                            from
                        )
                    )
                }
            } else {
                fileWriter.use {
                    it.write(
                        replaceMessageData.replaceAll(
                            "right", data.text,
                            from
                        )
                    )
                }
            }
        } else {
            if (!to.equals(userName)) {
                val fileWriter = FileWriter(htmlFilePath, true)
                fileWriter.use { it.write(replaceMessageData.replaceAll("left", data.text, from)) }
            } else {
                val fileWriter = FileWriter(htmlFilePath, true)
                fileWriter.use {
                    it.write(replaceMessageData.replaceAll("left", data.text, to))
                }
            }
        }
    }
    return 1
}


fun readJsonDataFromFile(itemJsonFilePath: Path, htmlFilePath: String) {

    var jsonData = File(itemJsonFilePath.toString()).forEachLine {
        try {

            if (jsonEditor(it, htmlFilePath) == 1)/* println("Data wrote to file")*/
            else println("Error with json data in JsonEditor")

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}


fun fetchFilePaths(path: Path): MutableList<Path> {      // метод для прохода по директории и сбора всех путей к файлам дампов

    val list: MutableList<Path> = mutableListOf()
    try {
        Files.walk(path)                                // проход по кадом файле
            .filter { item -> Files.isRegularFile(item) }
            .forEach { item -> list.add(item) }
        return list
    } catch (ex: IOException) {
        ex.printStackTrace()
    }
    return list
}


fun writeFirstPartHtmlFile(newHtmlFilePath: String): Int {
    val constant = Constant()
    val fileWriter = FileWriter(newHtmlFilePath, false)
    fileWriter.use { it.write(constant.htmlStartConstant) }
    //fileWriter.flush()
    // fileWriter.close()
    return 1

}


fun writeEndPartHtmlFile(newHtmlFilePath: String) {
    val constant = Constant()
    val fileWriter = FileWriter(newHtmlFilePath, true)
    fileWriter.use { it.write(constant.htmlEndConstant) }
    //fileWriter.flush()
    //fileWriter.close()
}


fun changeFileExtension(fileName: Path): String {

    val constant = Constant()
    val filename = fileName.fileName
    var newFileNameHTML = filename.toString().replace(".jsonl", ".html")
    var newHtmlFilePath = constant.filePAth.toString() + "\\" + newFileNameHTML
    return newHtmlFilePath
}