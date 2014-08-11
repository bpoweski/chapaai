package com.derbysoft.chapaai.adapter.pusher.utils;

import com.derbysoft.chapaai.adapter.pusher.domain.model.ChapaaiMappingEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Hotel;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RepositoryUtils {

     public static <T extends ChapaaiMappingEntry>  List<T> filterDisabled(List<T> ChapaaiMappingEntrys, boolean disabled){

         List<T> result = new LinkedList<T>(ChapaaiMappingEntrys);
         ListIterator<T> iterator = result.listIterator();
         while(iterator.hasNext()){
             T ChapaaiMappingEntry = iterator.next();
             if(ChapaaiMappingEntry.isDisabled() != disabled){
                 iterator.remove();
             }
         }
         return  result;
    }
}
