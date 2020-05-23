//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.json;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONArray<E> extends ArrayList<E> 
                          implements JSONAware, JSONStreamAware {
    private static final long serialVersionUID = 3957988303675231981L;

    public JSONArray() {}

    public static void writeJSONString(List list, Writer out, boolean flush) throws IOException {
        if (list == null) {
            out.write("null");
        } else {
            boolean first = true;
            Iterator iter = list.iterator();
            out.write(91);

            while(iter.hasNext()) {
                if (first) {
                    first = false;
                } else {
                    out.write(44);
                }

                Object value = iter.next();
                if (value == null) {
                    out.write("null");
                } else {
                    JSONValue.writeJSONString(value, out, flush);
                }
            }

            out.write(93);
            if (flush)
                out.flush();
        }
    }

    public void writeJSONString(Writer out, boolean flush) throws IOException {
        writeJSONString(this, out ,flush);
    }

    public static String toJSONString(List list) {
        if (list == null) {
            return "null";
        } else {
            boolean first = true;
            StringBuffer sb = new StringBuffer();
            Iterator iter = list.iterator();
            sb.append('[');

            while(iter.hasNext()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(',');
                }

                Object value = iter.next();
                if (value == null) {
                    sb.append("null");
                } else {
                    sb.append(JSONValue.toJSONString(value));
                }
            }

            sb.append(']');
            return sb.toString();
        }
    }

    public String toJSONString() {
        return toJSONString(this);
    }

    public String toString() {
        return this.toJSONString();
    }
}
