package it.grimi.modularserver.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author agrimandi
 */
public class ModuleUtilities
{

    /**
     *
     * @param url
     * @return
     */
    protected Map<String, String> getParams(String url)
    {
        Map<String, String> params = new HashMap<>();
        for (String param : url.split("&"))
        {
            String pair[] = param.split("=");
            if (pair.length > 1)
            {
                params.put(pair[0], pair[1]);
            } else
            {
                params.put(pair[0], "");
            }
        }
        return params;
    }

}
