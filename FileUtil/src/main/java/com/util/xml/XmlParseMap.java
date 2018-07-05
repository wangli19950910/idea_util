package com.util.xml;

import jdk.internal.util.xml.impl.Input;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author:wangli
 * @Description: 将xml格式的字符串转换成map
 * @Date: created in 2018/7/2
 */
public class XmlParseMap {
    public static void main(String[] args) {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<Message>" +
                "<Header>" +
                "<pubReqResponeTp>I</pubReqResponeTp>" +
                "<pubMacBrnNo>00</pubMacBrnNo>" +
                "<pubMsgTextMacNo></pubMsgTextMacNo>" +
                "<pubPinSeedNm>1530081018163</pubPinSeedNm>" +
                "<pubSecretKeyVerNo>0</pubSecretKeyVerNo>" +
                "<pubChnl>00</pubChnl>" +
                "<pubChnlGoto></pubChnlGoto>" +
                "<pubEncryptnTrnFlg>0</pubEncryptnTrnFlg>" +
                "<pubChkPwdFlg>0</pubChkPwdFlg>" +
                "<pubCombinatnsTrnFlg>0</pubCombinatnsTrnFlg>" +
                "<pubMsgTextTrackNo>12857491e8e14224a4929e6420b3eb55</pubMsgTextTrackNo>" +
                "<pubSrcAddrInfo>127.0.0.1</pubSrcAddrInfo>" +
                "<pubSecondarySubmitFlg>0</pubSecondarySubmitFlg>" +
                "<pubFsideTrnCd>7308</pubFsideTrnCd>" +
                "<pubFsideJnno>12857491e8e14224a4929e6420b3eb55</pubFsideJnno>" +
                "<pubFsideDt>20180627</pubFsideDt>" +
                "<pubTrnCd>15008</pubTrnCd>" +
                "<pubSubTrnCd></pubSubTrnCd>" +
                "<pubTrnPatternTp>1</pubTrnPatternTp>" +
                "<pubLegalPsnCd>9999</pubLegalPsnCd>" +
                "<pubBizBrn>0298</pubBizBrn>" +
                "<pubTellCd>00011</pubTellCd>" +
                "<pubAuthTell></pubAuthTell>" +
                "<pubAuthPwd></pubAuthPwd>" +
                "</Header>" +
                "<Body>" +
                "<queryPrFlg>0</queryPrFlg>" +
                "<bizBrn>0101</bizBrn>" +
                "<tellAttrCd>0</tellAttrCd>" +
                "<pageBegingSeqNo>1</pageBegingSeqNo>" +
                "<queryNum>10</queryNum>" +
                "</Body>" +
                "</Message>";
        String charset = "UTF-8";
        SAXReader reader = new SAXReader();
        try {
            InputStream ins = new ByteArrayInputStream(xml.getBytes(charset));
            Document document = reader.read(ins);
            Element root = document.getRootElement();
            Map<String,Object> resultMap = new HashMap<String, Object>();
            xmlToMap(root,resultMap);
            System.out.println(resultMap);



        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


    public static Map<String,Object> xmlToMap(Element tmpElement,Map<String,Object> resultMap){
        if(tmpElement.isTextOnly()){
            resultMap.put(tmpElement.getName(),tmpElement.getText());
        }
        Iterator<Element> iterator = tmpElement.elementIterator();
        while (iterator.hasNext()){
            Element element =iterator.next();
            xmlToMap(element,resultMap);
        }
        return resultMap;
    }
}
