class ReplaceMessageData {

    fun replaceMessageTextPosition(message : String, messagePosition: String): String {
        var messagePos = message
        var newMessagePosition = messagePos.replace("@position", messagePosition)
        return newMessagePosition
    }


    fun replaceMessageText(message : String, messageData : String) : String{
        var messageText = message
        var newMessageText = messageText.replace("@messageText", messageData)
        return newMessageText
    }

    fun replaceUserName(message: String, printUserName : String):String{

        var messageText = message
        var newMessageText = messageText.replace("@printusername", printUserName )
        return newMessageText

    }



    fun replaceAll(positionOfMessage : String, messageData: String, userName: String) : String{
        val constant = Constant()
        var messageText = constant.message
        messageText = replaceMessageTextPosition(messageText, positionOfMessage)
        messageText = replaceMessageText(messageText,messageData)
        messageText = replaceUserName(messageText, userName)
        return messageText
    }
}