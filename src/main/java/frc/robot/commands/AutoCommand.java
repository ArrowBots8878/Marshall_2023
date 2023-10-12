// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmElevator;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.Drivertrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Paddle;

public class AutoCommand extends SequentialCommandGroup {
  private Drivertrain m_driveTrain; 
  private ArmElevator m_elevator;

  /** Creates a new SampleAutoCommand. */
  public AutoCommand(Drivertrain driveTrain, ArmElevator elevator, Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_elevator = elevator;
 
    this.addCommands(new IntakeOutCommand(intake).raceWith(new WaitCommand(3)));
    this.addCommands(new DriveWithJoy(() -> -.75, () -> 0, m_driveTrain).withTimeout(1.5));
    this.addCommands(new AutoBalanceAlternate(m_driveTrain));
    this.addCommands(new SetBrakeMode(driveTrain));
  }
}
