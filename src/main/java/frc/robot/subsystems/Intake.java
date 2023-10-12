// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private CANSparkMax intake;

  /** Creates a new Intake. */
  public Intake() {
    intake=new CANSparkMax(4, MotorType.kBrushed);
    intake.setInverted(false);
    intake.setIdleMode(IdleMode.kBrake);
    intake.burnFlash();
    }
    public void runIn(){
      intake.set(0.5);
    }
    public void runOut(){
      intake.set(-0.5);
    }
    public void stop(){
      intake.set(0);
    }
}