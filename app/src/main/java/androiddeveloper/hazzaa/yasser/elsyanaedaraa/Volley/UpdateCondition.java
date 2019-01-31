package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateCondition extends StringRequest {
    private static final String images_url= "http://maintenanceksa.com/WebServices/UpdateCondition.php";
    private Map<String, String> params;

    public UpdateCondition(Response.Listener<String> listener, String type,String cond) {
        super(Method.POST, images_url, listener, null);
        params = new HashMap<>();
        params.put("ID_Air",type);
        params.put("cond",cond);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}