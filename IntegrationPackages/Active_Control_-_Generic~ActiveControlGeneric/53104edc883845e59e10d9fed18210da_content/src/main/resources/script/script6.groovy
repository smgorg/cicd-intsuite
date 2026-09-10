/* Refer the link below to learn more about the use cases of script.
https://help.sap.com/viewer/368c481cd6954bdfa5d0435479fd4eaf/Cloud/en-GB/148851bf8192412cba1f9d2c17f4bd25.html

If you want to know more about the SCRIPT APIs, refer the link below
https://help.sap.com/doc/a56f52e1a58e4e2bac7f7adbf45b2e26/Cloud/en-GB/index.html */
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    //Body
def body = message.getBody(java.io.Reader);    

def json = new JsonSlurper().parse(body);

json.task.locked = json.task.locked.toBoolean()
json.task.deploymentStatus.statusIsOverridden = json.task.deploymentStatus.statusIsOverridden.toBoolean();
json.task.planningStatus.statusIsOverridden = json.task.planningStatus.statusIsOverridden.toBoolean();
json.task.ragStatuses.objectCount = convertToInt(json.task.ragStatuses.objectCount);
json.task.ragStatuses.keyCount = convertToInt(json.task.ragStatuses.keyCount);
//json.task.ragStatuses.statuses.each { item ->
//item.value = convertToInt(item.value)
//}

message.setBody(JsonOutput.toJson(json));

body = message.getBody(java.lang.String) as String; 
body = body.replaceAll("#lt#","<");
body = body.replaceAll("#gt#",">");
body = body.replaceAll("#ap#","&");
body = body.replaceAll("<p>","");
body = body.replaceAll("</p>","");
body = body.replaceAll("<br />","\\\\n");
body = body.substring(0, body.length() - 1)
body = body.substring(1, body.length())
body = body.replace("[\"\"]","[]")
message.setBody(body);

    return message;
}

def convertToInt(inputValue){
if(inputValue != null){
    if(inputValue == "0" || inputValue == 0){
        return 0
    }
    try {  
        return
Integer.parseInt(inputValue.toString())
    }
    catch (NumberFormatException e){
    return inputValue;
    }}
    else{
    return inputValue
    }
}