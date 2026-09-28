/*
Delay Message for 60 seconds
 */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
def Message processData(Message message) {
       sleep(60000);
       return message;
}