package com.brainxtech.todolist.presentation.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.brainxtech.extensions.compose_ui_utils.clickableWithoutRipple
import com.brainxtech.extensions.extension.isNull
import com.brainxtech.extensions.utils.Constants
import com.brainxtech.extensions.utils.Constants.EMPTY_STRING
import com.brainxtech.extensions.utils.Constants.ONE_FLOAT
import com.brainxtech.extensions.utils.Constants.TWO_FLOAT
import com.brainxtech.extensions.utils.Constants.ZERO
import com.brainxtech.extensions.utils.Constants.ZERO_FIVE_FLOAT
import com.brainxtech.extensions.utils.getHoursAndMinutesUsingCalendar
import com.brainxtech.todolist.R
import com.brainxtech.todolist.datasource.appUtils.PermissionTextProvider
import com.brainxtech.todolist.presentation.theme.TodoListAppColors
import com.brainxtech.todolist.presentation.theme.TodoListAppColors.DialogBackgroundColor
import com.brainxtech.todolist.presentation.theme.TodoListAppColors.blackTextColor
import com.brainxtech.todolist.presentation.theme.TodoListAppColors.cardBackground
import java.time.LocalTime
import java.util.Calendar

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ConfirmationDialog(
    title: String,
    description: String,
    buttonText: String,
    cancelButtonText: String,
    icon:Int?=null,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            usePlatformDefaultWidth = true,
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            decorFitsSystemWindows = false
        )
    )
    {
        Card(
            modifier = Modifier.dialogModifier(),
            shape = dialogCardShape(),
            colors = CardColors(containerColor = Color.White, contentColor = Color.White, disabledContainerColor =  Color.White, disabledContentColor = Color.White),
        ) {
            ConstraintLayout(modifier = Modifier.padding(DimenDp.DefaultPadding)) {
                val (closeBtn,titleLayout,desc,buttons) = createRefs()
                Image(painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = Constants.EMPTY_STRING,
                    modifier = Modifier
                        .clickableWithoutRipple { onDismiss() }
                        .constrainAs(closeBtn) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                )
                FlowRow(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(titleLayout) {
                        top.linkTo(closeBtn.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                    icon?.let {
                        Image(painter = painterResource(id = it), contentDescription = EMPTY_STRING)
                        Spacer(modifier = Modifier.width(DimenDp.ExtraSmallMargin))
                    }
                    HeadlineSmallText(modifier = Modifier.wrapContentSize(), text = title, textAlign = TextAlign.Start, color = TodoListAppColors.menuSelectedTextColor)
                }
                DisplaySmallText(modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(desc) {
                        top.linkTo(titleLayout.bottom, margin = DimenDp.ExtraSmallMargin)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = description, textAlign = TextAlign.Start, color = TodoListAppColors.blackTextColor)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .constrainAs(buttons) {
                            top.linkTo(desc.bottom, margin = DimenDp.SmallSpace)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    horizontalArrangement = Arrangement.spacedBy(DimenDp.SmallSpace),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoundedBorderCenteredContentCorneredButton(
                        buttonText = cancelButtonText,
                        buttonColor = TodoListAppColors.greyBackgroundColor,
                        textColor = TodoListAppColors.menuSelectedTextColor,
                        modifier = Modifier.weight(ONE_FLOAT)
                    ) {onDismiss()}

                    GradientRoundedBorderCorneredButton(
                        buttonText = buttonText,
                        gradientColor = TodoListAppColors.primaryHorizontalGradientColor,
                        textColor = Color.White,
                        modifier = Modifier.weight(ONE_FLOAT)
                    ) {
                        onOkClick()
                        onDismiss()
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    selectedDate:Long?=null,
    onDateSelected: (Long?) -> Unit,
    onCancel: () -> Unit,
) {
    val state = rememberDatePickerState(initialSelectedDateMillis = selectedDate)
    DatePickerDialog(
        onDismissRequest = { onCancel() },
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(state.selectedDateMillis)
                onCancel()
            }) {
                HeadlineSmallText(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = R.string.done),
                    textAlign = TextAlign.Center,
                    color = TodoListAppColors.secondaryColor
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                if (!selectedDate.isNull()){
                    onDateSelected(null)
                }
                onCancel()
            }) {
                HeadlineSmallText(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = if(selectedDate.isNull()) R.string.cancel else R.string.unset),
                    textAlign = TextAlign.Center,
                    color = TodoListAppColors.redTextColor
                )
            }
        }
    )
    {
        DatePicker(
            state = state,
            colors = DatePickerDefaults.colors(
                containerColor = Color.White,
                selectedDayContainerColor = TodoListAppColors.blueContainerColor,
                selectedDayContentColor = Color.White,
                selectedYearContainerColor = TodoListAppColors.blueContainerColor,
                selectedYearContentColor = Color.White,
                todayContentColor = TodoListAppColors.blueContainerColor,
                todayDateBorderColor = TodoListAppColors.blueContainerColor,
                dividerColor = TodoListAppColors.dividerColor,
                disabledYearContentColor = TodoListAppColors.greyTextColor.copy(alpha = ZERO_FIVE_FLOAT),
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePicker(
    selectedTime:Long?=null,
    onTimeSelected: (Long?) -> Unit,
    onCancel: () -> Unit
) {
    val timeOfDay = selectedTime.getHoursAndMinutesUsingCalendar()
    val state = rememberTimePickerState(initialHour = timeOfDay?.first ?: LocalTime.now().hour, initialMinute = timeOfDay?.second ?: LocalTime.now().minute ,is24Hour = false,)
    DatePickerDialog(
        colors = DatePickerDefaults.colors(containerColor = Color.White),
        onDismissRequest = { onCancel() },
        confirmButton = {
            TextButton(onClick = {
                onTimeSelected(Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, state.hour)
                    set(Calendar.MINUTE, state.minute)
                    set(Calendar.SECOND, ZERO)
                }.timeInMillis)
                onCancel()

            }) {
                HeadlineSmallText(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = R.string.done),
                    textAlign = TextAlign.Center,
                    color = TodoListAppColors.secondaryColor
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                if (!selectedTime.isNull()){
                    onTimeSelected(null)
                }
                onCancel()
            }) {
                HeadlineSmallText(
                    modifier = Modifier.wrapContentSize(),
                    text = stringResource(id = if(selectedTime.isNull()) R.string.cancel else R.string.unset),
                    textAlign = TextAlign.Center,
                    color = TodoListAppColors.redTextColor
                )
            }
        }
    )
    {
        TimePicker(
            modifier = Modifier.padding(DimenDp.MediumPadding),state = state,
            colors = TimePickerDefaults.colors(
                containerColor = Color.White,
                timeSelectorSelectedContainerColor = TodoListAppColors.blueContainerColor,
                timeSelectorUnselectedContainerColor = TodoListAppColors.cardBackground,
                selectorColor = TodoListAppColors.blueContainerColor,
                periodSelectorUnselectedContainerColor = TodoListAppColors.cardBackground,
                periodSelectorBorderColor = TodoListAppColors.cardBackground,
                periodSelectorSelectedContainerColor = TodoListAppColors.blueContainerColor,
                clockDialColor = TodoListAppColors.cardBackground,

            ))
    }
}

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = true, dismissOnBackPress = false, dismissOnClickOutside = false, decorFitsSystemWindows = false))
    {
        Card(
            modifier = DialogModifier(),
            shape = DialogCardShape(),
            colors = CardColors(containerColor = cardBackground, contentColor = MaterialTheme.colorScheme.onSurface, disabledContainerColor =  DialogBackgroundColor, disabledContentColor = MaterialTheme.colorScheme.onSurface),
        ) {
            Column(
                Modifier
                    .wrapContentSize()
                    .background(cardBackground),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(DimenDp.SmallSpace))
                DisplayMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(DimenDp.ExtraSmallPadding),
                    text = stringResource(id = R.string.ask_for_permission),
                    color = blackTextColor, textAlign =  TextAlign.Center
                )
                DisplaySmallText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(DimenDp.ExtraSmallPadding),
                    text = stringResource(id = permissionTextProvider.getDescription(isPermanentlyDeclined)),
                    color = blackTextColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(DimenDp.MediumPadding))
                HorizontalDivider(color = TodoListAppColors.dividerColor)
                AnimatedVisibility(visible = isPermanentlyDeclined) {
                    DisplayMediumText(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(2f)
                            .padding(vertical = DimenDp.DefaultPadding)
                            .clickableWithoutRipple {
                                if (isPermanentlyDeclined) {
                                    onGoToAppSettingsClick()
                                } else {
                                    onOkClick()
                                }
                            },
                        text = stringResource(id =if (isPermanentlyDeclined) R.string.grant_permission else R.string.ok ),
                        color = blackTextColor
                    )
                }
                AnimatedVisibility(visible = !isPermanentlyDeclined) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        DisplayMediumText(
                            modifier = Modifier
                                .wrapContentHeight()
                                .weight(TWO_FLOAT)
                                .padding(vertical = DimenDp.DefaultPadding)
                                .clickableWithoutRipple {
                                    onDismiss()
                                },
                            text = stringResource(id =R.string.dont_allow_),
                            color = blackTextColor
                        )
                        VerticalDivider(color = TodoListAppColors.dividerColor, modifier = Modifier.height(buttonSize()))
                        DisplayMediumText(
                            modifier = Modifier
                                .wrapContentHeight()
                                .weight(TWO_FLOAT)
                                .padding(vertical = DimenDp.DefaultPadding)
                                .clickableWithoutRipple {
                                    if (isPermanentlyDeclined) {
                                        onGoToAppSettingsClick()
                                    } else {
                                        onOkClick()
                                    }
                                },
                            text = stringResource(id =if (isPermanentlyDeclined) R.string.grant_permission else R.string.ok ),
                            color = blackTextColor
                        )
                    }

                }

            }
        }

    }
}

@Composable
fun GenericDialog(
    message:Any,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
){
    val messageString = when (message) {
        is String -> message
        is Int -> LocalContext.current.getString(message)
        else -> null
    }
    if (messageString.isNullOrEmpty()) return
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = true, dismissOnBackPress = false, dismissOnClickOutside = false, decorFitsSystemWindows = false))
    {

        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(usePlatformDefaultWidth = true, dismissOnBackPress = false, dismissOnClickOutside = false, decorFitsSystemWindows = false))
        {
            Card(
                modifier = DialogModifier(),
                shape = DialogCardShape(),
                colors = CardColors(containerColor = cardBackground, contentColor = blackTextColor, disabledContainerColor =  cardBackground, disabledContentColor = blackTextColor),
            ) {
                Column(
                    Modifier
                        .wrapContentSize()
                        .background(cardBackground),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(DimenDp.MediumPadding))
                    DisplayMediumText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(DimenDp.ExtraSmallPadding),
                        text = messageString,
                        color = blackTextColor,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(DimenDp.MediumPadding))
                    HorizontalDivider(color = TodoListAppColors.dividerColor)
                    DisplayMediumText(
                        modifier = Modifier
                            .height(buttonSize())
                            .fillMaxWidth()
                            .padding(vertical = DimenDp.DefaultPadding)
                            .clickableWithoutRipple {
                                onOkClick()
                            },
                        text = stringResource(id = R.string.ok ),
                        color = blackTextColor
                    )
                }
            }

        }

    }
}