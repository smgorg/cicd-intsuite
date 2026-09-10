import com.sap.gateway.ip.core.customdev.util.Message


def Message processData(Message message) {

def body = message.getBody(java.lang.String) as String;

body = body.replaceAll("&amp;lt;","#lt#");
body = body.replaceAll("&amp;gt;","#gt#");
body = body.replaceAll("&amp;amp;","#ap#");
body = body.replaceAll("[^\\u0000-\\uFFFF]", "");
message.setBody(body);

    return message;
}