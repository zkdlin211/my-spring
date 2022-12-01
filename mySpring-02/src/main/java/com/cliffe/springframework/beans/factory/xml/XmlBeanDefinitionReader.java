package com.cliffe.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.cliffe.springframework.BeanException;
import com.cliffe.springframework.beans.PropertyValue;
import com.cliffe.springframework.beans.PropertyValues;
import com.cliffe.springframework.beans.factory.config.BeanDefinition;
import com.cliffe.springframework.beans.factory.config.BeanReference;
import com.cliffe.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.cliffe.springframework.beans.factory.support.BeanDefinitionReader;
import com.cliffe.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.cliffe.springframework.core.io.Resource;
import com.cliffe.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAKey;

/**
 * @Author: Cliffe
 * @Date: 2022-12-01 12:57 下午
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try {
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanException("IOException parsing XML document from" + resource, e);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException, BeanException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (!(node instanceof Element) ||
                    !"bean".equals(node.getNodeName())) continue;
            //parsing <bean> label
            Element bean = (Element) node;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //get bean definition
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            //read and fill the attributes
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                Node item = bean.getChildNodes().item(j);
                if (!(item instanceof Element) ||
                        !"property".equals(item.getNodeName())) continue;
                //parsing <property> label
                Element property = (Element) item;
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                //get reference bean
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                //create and add a new propertyValue
                // as container of a pair of property
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(attrName, value));
            }
            if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            //registry beanDefinition
            getBeanDefinitionRegistry().registerBeanDefinition(beanName, beanDefinition);
        }

    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
}
