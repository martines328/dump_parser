import java.nio.file.Path
import java.nio.file.Paths

class Constant {

    val filePAth: Path = Paths.get("src/main/kotlin/files/")
    val htmlStartConstant = "<html>\n" +
            "<head>\n" +
            "    <style>\n" +
            "        .speech{\n" +
            "            display: inline-block;\n" +
            "           background-color: yellow;\n" +
            "            padding: 10px;\n" +
            "            font-size: 15px;\n" +
            "            border-radius: 25px;\n" +
            "            color: black;\n" +
            "            word-wrap: break-word;\n" +
            "            clear: both;\n" +
            "            margin: 5px;\n" +
            "            width: fit-content;\n" +
            "            max-width: 400;\n" +
            "\n" +
            "        }\n" +
            "        \n" +
            "    </style>\n" +
            "</head>\n" +
            "<body  style=\"background-color: #1B95F2;\">\n"
    val htmlEndConstant = "\n" +
            "\n" +
            "</body>\n" +
            "</html>\n" +
            "\n"

    val message = "<div class=\"speech\"  style=\"text-align: @position ;float: @position; \"> @printusername <br> @messageText</div>\n" +
            "<br>"



}