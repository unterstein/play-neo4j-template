package helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewEnumModel implements Serializable {

  public List<String> keys = new ArrayList<String>();

  public Map<String, String> values = new HashMap<String, String>();
}
