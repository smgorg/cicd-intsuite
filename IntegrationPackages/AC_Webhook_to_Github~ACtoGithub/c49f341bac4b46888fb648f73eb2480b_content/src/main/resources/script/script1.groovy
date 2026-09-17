/* Refer the link below to learn more about the use cases of script.
https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-US/148851bf8192412cba1f9d2c17f4bd25.html

If you want to know more about the SCRIPT APIs, refer the link below
https://help.sap.com/doc/a56f52e1a58e4e2bac7f7adbf45b2e26/Cloud/en-US/index.html */
import com.sap.gateway.ip.core.customdev.util.Message;

def Message processData(Message message) {


    //Get header value for custom field ID
    
    def headers = message.getHeaders();
    def cf = headers.get("customField");
    cf = "CF_" + cf
    
    // Get the XML payload.    
    def payload = message.getBody(String)    

    // Parse it using XmlSlurper. That second false is to ignore namespaces.    
    payload = new XmlSlurper(false, false).parseText(payload)    

    // Extract the value of the custom field   

    def guid = payload.'**'.find { node -> node.name() == cf }

    // Store the ID in a property.    

   message.setProperty("extId", guid)
   message.setProperty("extIdlen", guid.text().length())



    return message;
}