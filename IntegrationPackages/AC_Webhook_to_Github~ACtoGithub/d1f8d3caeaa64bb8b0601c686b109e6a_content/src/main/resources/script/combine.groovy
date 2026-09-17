import com.sap.it.api.mapping.*;

def String getheader(String header_name, MappingContext context) {
    def headervalue= context.getHeader(header_name);
    return headervalue;
}

def String getProperty(String property_name, MappingContext context) {
    def propValue= context.getProperty(property_name);
    return propValue;
}

def String setHeader(String header_name, String header_value, MappingContext context) { 
    context.setHeader(header_name, header_value);   
    return header_value;
}

def String setProperty(String property_name, String property_value, MappingContext context) {
    context.setProperty(property_name, property_value);    
    return header_value;
}

def String combineNodes(String[] inputs, MappingContext context) {
    // Filter out empty or null values and join them with a comma
    String result = inputs.findAll { it && !it.trim().isEmpty() }.join(", ")
    return result;
}

def void combineChildren(String[] childValues, Output output) {
    // Join all incoming child node values into one string separated by a comma
    if (childValues != null && childValues.length > 0) {
        String combined = childValues.findAll { it != null }.join(", ");
        output.addValue(combined);
    } else {
        output.addvalue("NONE");
    }
}