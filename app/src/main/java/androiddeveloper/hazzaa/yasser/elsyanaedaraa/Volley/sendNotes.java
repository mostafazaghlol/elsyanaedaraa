package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class sendNotes extends StringRequest {
    private static final String images_url= "http://maintenanceksa.com/WebServices/UpdateNotes.php";
    private Map<String, String> params;

    public sendNotes(Response.Listener<String> listener, String ID_Air,String notes) {
        super(Method.POST, images_url, listener, null);
        params = new HashMap<>();
        params.put("ID_Air",ID_Air);
        params.put("notes",notes);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}