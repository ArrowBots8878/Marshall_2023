// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivertrain;

public class AutobalanceCommand extends CommandBase {
private Drivertrain drivertrain; 

private boolean holding;
private Timer balanceTimer;

private double previousPitch;
private double currentPitch;
private double BALANCE_TOLERANCE_DEGREES=12;

/** Creates a new NewChargeStationAutoBalance. */
public AutobalanceCommand(Drivertrain drivertrain) {
    this.drivertrain = drivertrain;

    balanceTimer = new Timer();
    
    addRequirements(drivertrain);
}

public void reset() {
    holding = false;
    previousPitch = 0;
    currentPitch = 0;
    balanceTimer.stop();
    balanceTimer.reset();
}

// Called when the command is initially scheduled.
@Override
public void initialize() {
    holding = false;
    previousPitch = 0;
    currentPitch = 0;
}

@Override
public void execute(){
    previousPitch = currentPitch;
    currentPitch = drivertrain.getRoll();
    boolean isDropping = Math.abs(previousPitch) - Math.abs(currentPitch) > 0.125;
    boolean isLevel = Math.abs(drivertrain.getRoll()) < 3;

    if (!isDropping && !holding && !isLevel) {
drivertrain.my_DriveArchade(            Math.copySign(0.175, (double) -(drivertrain.getRoll())),
 0);

    } else {
        //if balance timer has gone for 2 seconds, x the wheels
        if (balanceTimer.hasElapsed(0.5)) {
      drivertrain.my_DriveArchade(0, 0);
      drivertrain.setBrake();
            holding = true;
            isLevel = true;
        //if the balance timer has NOT started and the bot is dropping, start timer
        } else if (!(balanceTimer.hasElapsed(0.1)) && isDropping) {
            balanceTimer.start();
        } else if (!balanceTimer.hasElapsed(1)) {
            //do nothing still dropping
        //if the pitch changes to greater than the tolerance, stop and reset the balance timer
        } else if ((Math.abs(drivertrain.getRoll())) > BALANCE_TOLERANCE_DEGREES) {
            balanceTimer.stop();
            balanceTimer.reset();
            holding = false;
            isDropping = false;
            isLevel = false;
        }
    }
}

@Override
public boolean isFinished() {
    return false;
}
}
