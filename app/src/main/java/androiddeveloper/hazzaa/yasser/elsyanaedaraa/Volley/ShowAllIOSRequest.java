package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ShowAllIOSRequest extends StringRequest {
    private static final String images_url= "http://maintenanceksa.com/WebServices/ShowData/AirCondition/showIOSAll.php";
    private Map<String, String> params;

    public ShowAllIOSRequest(Response.Listener<String> listener) {
        super(Method.POST, images_url, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}