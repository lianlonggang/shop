package com.shop.base.convert;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.shop.base.model.MyHashMap;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.collections.AbstractCollectionConverter;
import com.thoughtworks.xstream.core.ClassLoaderReference;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.DefaultMapper;


public class MapCustomConverter extends AbstractCollectionConverter {

    
	public MapCustomConverter() {
		super(new DefaultMapper(new ClassLoaderReference(Map.class.getClassLoader())));
	}
	public boolean canConvert(Class type) {
        return type.equals(HashMap.class)
                || type.equals(Hashtable.class)
                || type.equals(MyHashMap.class)
                || type.getName().equals("java.util.LinkedHashMap")
                || type.getName().equals("sun.font.AttributeMap") // Used by java.awt.Font in JDK 6
                ;
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Map map = (Map) source;
        MyXStream xStream = new MyXStream();  
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Entry entry = (Entry) iterator.next();
            ExtendedHierarchicalStreamWriterHelper.startNode(writer, entry.getKey().toString(), Entry.class);
            Object obj = entry.getValue();
            if (obj instanceof String) {
				String new_name = (String) obj;
				writer.setValue(new_name);
			}else{
				xStream.marshal(obj, writer);
			}
            writer.endNode();
        }
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Map map = (Map) createCollection(context.getRequiredType());
        populateMap(reader, context, map);
        return map;
    }

    protected void populateMap(HierarchicalStreamReader reader, UnmarshallingContext context, Map map) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            Object key = reader.getAttribute("key");
            Object value = reader.getAttribute("value");
            map.put(key, value);
            reader.moveUp();
        }
    }
    public static void main(String[] args) {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("test", "test");
    	map.put("a", "aaa");
		MyXStream x = new MyXStream();
		System.out.println(x.toXML(map));
	}
}

