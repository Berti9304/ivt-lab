package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private TorpedoStore firstTorpedo;
  private TorpedoStore secondTorpedo;
  private GT4500 ship;

  @Before
  public void init(){
    firstTorpedo = mock(TorpedoStore.class);
    secondTorpedo = mock(TorpedoStore.class);
    this.ship = new GT4500(firstTorpedo,secondTorpedo);

  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(firstTorpedo.fire(1)).thenReturn(true);
    when(secondTorpedo.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);


    // Assert
    verify(firstTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Secondary_Success(){
    // Arrange
    when(firstTorpedo.fire(1)).thenReturn(true);
    when(secondTorpedo.fire(1)).thenReturn(true);
    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(firstTorpedo, times(1)).fire(1);
    verify(secondTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Primary_After_Secondary_Success(){
    // Arrange
    when(firstTorpedo.fire(1)).thenReturn(true);
    when(secondTorpedo.fire(1)).thenReturn(true);
    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(firstTorpedo, times(2)).fire(1);
    verify(secondTorpedo, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Second_isEmpty(){
    // Arrange
    when(firstTorpedo.fire(1)).thenReturn(true);
    when(secondTorpedo.isEmpty()).thenReturn(true);
    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(firstTorpedo, times(2)).fire(1);

  }

  @Test
  public void fireTorpedo_First_isEmpty(){
    // Arrange
    when(firstTorpedo.isEmpty()).thenReturn(true);
    when(secondTorpedo.fire(1)).thenReturn(true);
    //Act
    ship.fireTorpedo(FiringMode.SINGLE);
    //Assert
    verify(secondTorpedo, times(1)).fire(1);

  }

  @Test
  public void fireTorpedo_ALL(){
    // Arrange
    when(firstTorpedo.fire(firstTorpedo.getTorpedoCount())).thenReturn(true);
    when(secondTorpedo.fire(secondTorpedo.getTorpedoCount())).thenReturn(true);
    //Act
    ship.fireTorpedo(FiringMode.ALL);
    //Assert
    verify(firstTorpedo,times(2)).fire(firstTorpedo.getTorpedoCount());
    verify(secondTorpedo,times(2)).fire(secondTorpedo.getTorpedoCount());

  }



}
