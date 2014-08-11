package com.derbysoft.chapaai.adapter.pusher.utils;

import com.derbysoft.chapaai.adapter.pusher.domain.model.ChapaaiMappingEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryUtilsTest {

    @Test
    public void testFilterDisableTrue(){
        ChapaaiMappingEntry trueEntry = mock(ChapaaiMappingEntry.class);
        when(trueEntry.isDisabled()).thenReturn(true);
        ChapaaiMappingEntry falseEntry = mock(ChapaaiMappingEntry.class);
        when(falseEntry.isDisabled()).thenReturn(false);

        List<ChapaaiMappingEntry> entryList = Arrays.asList(trueEntry,falseEntry);


        List<ChapaaiMappingEntry> resultList = RepositoryUtils.filterDisabled(entryList, true);

        Assert.assertEquals(1,resultList.size());
        Assert.assertTrue(resultList.contains(trueEntry));

    }


    @Test
    public void testFilterDisableFalse(){
        ChapaaiMappingEntry trueEntry = mock(ChapaaiMappingEntry.class);
        when(trueEntry.isDisabled()).thenReturn(true);
        ChapaaiMappingEntry falseEntry = mock(ChapaaiMappingEntry.class);
        when(falseEntry.isDisabled()).thenReturn(false);

        List<ChapaaiMappingEntry> entryList = Arrays.asList(trueEntry,falseEntry);


        List<ChapaaiMappingEntry> resultList = RepositoryUtils.filterDisabled(entryList, false);

        Assert.assertEquals(1,resultList.size());
        Assert.assertTrue(resultList.contains(falseEntry));

    }
}
