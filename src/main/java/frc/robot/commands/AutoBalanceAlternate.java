// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ProxyCommand;
import frc.robot.subsystems.Drivertrain;



public class AutoBalanceAlternate extends CommandBase {
  private final Drivertrain m_Drivertrain;
  private double pitchSetPoint;
  private PIDController m_PidController;
  /** Creates a new AutoBalnceAlternate. */
  public AutoBalanceAlternate(Drivertrain m_Drivertrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_Drivertrain = m_Drivertrain;
    pitchSetPoint = -3;
    addRequirements(m_Drivertrain);
    m_PidController = new PIDController(0.0065, 0, 0);
    m_PidController.setSetpoint(pitchSetPoint);
    m_PidController.setTolerance(1.11);
   
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    double motorOutput = m_PidController.calculate(m_Drivertrain.getRoll());
    if(motorOutput < 0)
     motorOutput += -0.185;
    else
      motorOutput += 0.185;


    m_Drivertrain.my_DriveArchade(motorOutput, 0);
    System.out.println(motorOutput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Done");
    Command endCommand = new SetBrakeMode(m_Drivertrain);
    endCommand.schedule();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_PidController.atSetpoint();
  }
}
