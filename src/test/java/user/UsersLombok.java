package user;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.json.JSONObject;

import java.util.jar.JarException;

@Builder
@Data
@Setter
public class UsersLombok {

    private Integer postId;
    private String name;
    private String email;
    private String body;

    public JSONObject getJson() throws JarException{
        return new JSONObject(new Gson().toJson(this));
    }
}
