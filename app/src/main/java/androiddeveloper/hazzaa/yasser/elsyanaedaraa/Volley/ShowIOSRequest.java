package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ShowIOSRequest extends StringRequest {
    private static final String images_url= "http://maintenanceksa.com/WebServices/ShowData/AirCondition/showIOS.php";
    private Map<String, String> params;

    public ShowIOSRequest(Response.Listener<String> listener, String type) {
        super(Method.POST, images_url, listener, null);
        params = new HashMap<>();
        params.put("type",type);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}