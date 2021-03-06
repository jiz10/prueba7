// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.edu.um.domain;

import ar.com.edu.um.domain.Negocio;
import ar.com.edu.um.domain.NegocioDataOnDemand;
import ar.com.edu.um.domain.NegocioIntegrationTest;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect NegocioIntegrationTest_Roo_IntegrationTest {
    
    declare @type: NegocioIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: NegocioIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: NegocioIntegrationTest: @Transactional;
    
    @Autowired
    NegocioDataOnDemand NegocioIntegrationTest.dod;
    
    @Test
    public void NegocioIntegrationTest.testCountNegocios() {
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", dod.getRandomNegocio());
        long count = Negocio.countNegocios();
        Assert.assertTrue("Counter for 'Negocio' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void NegocioIntegrationTest.testFindNegocio() {
        Negocio obj = dod.getRandomNegocio();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to provide an identifier", id);
        obj = Negocio.findNegocio(id);
        Assert.assertNotNull("Find method for 'Negocio' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Negocio' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void NegocioIntegrationTest.testFindAllNegocios() {
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", dod.getRandomNegocio());
        long count = Negocio.countNegocios();
        Assert.assertTrue("Too expensive to perform a find all test for 'Negocio', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Negocio> result = Negocio.findAllNegocios();
        Assert.assertNotNull("Find all method for 'Negocio' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Negocio' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void NegocioIntegrationTest.testFindNegocioEntries() {
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", dod.getRandomNegocio());
        long count = Negocio.countNegocios();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Negocio> result = Negocio.findNegocioEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Negocio' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Negocio' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void NegocioIntegrationTest.testFlush() {
        Negocio obj = dod.getRandomNegocio();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to provide an identifier", id);
        obj = Negocio.findNegocio(id);
        Assert.assertNotNull("Find method for 'Negocio' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyNegocio(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Negocio' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void NegocioIntegrationTest.testMergeUpdate() {
        Negocio obj = dod.getRandomNegocio();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to provide an identifier", id);
        obj = Negocio.findNegocio(id);
        boolean modified =  dod.modifyNegocio(obj);
        Integer currentVersion = obj.getVersion();
        Negocio merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Negocio' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void NegocioIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", dod.getRandomNegocio());
        Negocio obj = dod.getNewTransientNegocio(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Negocio' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Negocio' identifier to be null", obj.getId());
        try {
            obj.persist();
        } catch (final ConstraintViolationException e) {
            final StringBuilder msg = new StringBuilder();
            for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                final ConstraintViolation<?> cv = iter.next();
                msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
            }
            throw new IllegalStateException(msg.toString(), e);
        }
        obj.flush();
        Assert.assertNotNull("Expected 'Negocio' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void NegocioIntegrationTest.testRemove() {
        Negocio obj = dod.getRandomNegocio();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Negocio' failed to provide an identifier", id);
        obj = Negocio.findNegocio(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Negocio' with identifier '" + id + "'", Negocio.findNegocio(id));
    }
    
}
