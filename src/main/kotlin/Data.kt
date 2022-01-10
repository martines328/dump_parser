package files


data class Data(
    val event: String,
    val id: String,
    val flags: Int,
    val reply_id: String,
    val from: From,
    val to: To,
    val out: String,
    val unread: String,
    val service: String,
    val date: String,
    val text: String

)


data class From(

    val id: String,
    val peer_type: String,
    val peer_id: Int,
    val print_name: String,
    val flags: Int,
    val first_name: String,
    val whenFrom: String,
    val last_name: String,
    val phone: String,
    val username: String,
)


data class To(

    val id: String,
    val peer_type: String,
    val peer_id: Int,
    val print_name: String,
    val flags: Int,
    val first_name: String,
    val whenTO : String,
    val last_name: String,
    val username: String,
)

