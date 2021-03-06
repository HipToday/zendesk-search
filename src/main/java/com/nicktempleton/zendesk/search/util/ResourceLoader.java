/*
 * Copyright (c) 2019 Nick Templeton
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.nicktempleton.zendesk.search.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ResourceLoader {

    private ResourceLoader() {
        // no need to instantiate me
    }

    /**
     * Deserialize the JSON in the given resource (expected to be a file in the
     * resources directory) to a list of maps where the map keys are the field
     * names and the values are appropriate objects as determined by Gson.
     * 
     * @param resource Resource JSON file to deserialize
     * @return A list of maps read from the JSON file
     */
    public static List<Map<String, Object>> loadFromJsonResource(String resource) {
        List<Map<String, Object>> data = new ArrayList<>();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (
            InputStream is = classloader.getResourceAsStream(resource);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Map<String, Object>>>(){}.getType();
            data = gson.fromJson(br, listType);
        } catch (NullPointerException npe) {
            System.err.println("Unable to open resource: " + resource);
            npe.printStackTrace();
        } catch (JsonSyntaxException jse) {
            System.err.println("Invalid JSON in resource: " + resource);
            jse.printStackTrace();
        } catch (IOException ioe) {
            System.err.println("Something really went wrong loading resource: " + resource);
            ioe.printStackTrace();
        }

        return data;
    }
}
