package androiddeveloper.hazzaa.yasser.elsyanaedaraa.Volley;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class updateParameter extends StringRequest {
    private static final String images_url= "http://maintenanceksa.com/WebServices/UpdateView.php";
    private Map<String, String> params;

    public updateParameter(Response.Listener<String> listener,String ID_Air) {
        super(Method.POST, images_url, listener, null);
        params = new HashMap<>();
        params.put("ID_Air",ID_Air);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}