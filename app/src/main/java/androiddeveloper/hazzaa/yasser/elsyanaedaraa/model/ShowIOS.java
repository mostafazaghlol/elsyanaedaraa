
package androiddeveloper.hazzaa.yasser.elsyanaedaraa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowIOS {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("ID_Air")
    @Expose
    private String iDAir;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Lat")
    @Expose
    private String lat;
    @SerializedName("Lan")
    @Expose
    private String lan;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("cond")
    @Expose
    private String cond;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("view")
    @Expose
    private String view;
    @SerializedName("more")
    @Expose
    private String more;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("Description")
    @Expose
    private Object description;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getIDAir() {
        return iDAir;
    }

    public void setIDAir(String iDAir) {
        this.iDAir = iDAir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

}
