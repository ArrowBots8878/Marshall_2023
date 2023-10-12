// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmElevator;
import frc.robot.subsystems.ArmExtension;
import frc.robot.subsystems.Drivertrain;
import frc.robot.subsystems.Paddle;

public class CableSideAuto extends SequentialCommandGroup {
  private Drivertrain m_driveTrain; 
  private ArmElevator m_elevator;
  private ArmExtension m_extension;
  private Paddle m_paddle;
  /** Creates a new SampleAutoCommand. */
  public CableSideAuto(Drivertrain driveTrain, ArmElevator elevator, ArmExtension extension, Paddle paddle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_elevator = elevator;
    m_extension = extension;
    m_paddle = paddle;

        this.addCommands(new DriveWithJoy(() -> -.75, () -> 0, m_driveTrain).withTimeout(2.3));

  }
}
