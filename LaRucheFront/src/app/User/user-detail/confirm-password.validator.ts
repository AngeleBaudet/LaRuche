import { AbstractControl, FormGroup } from '@angular/forms';

export function ConfirmPasswordValidator(
  controlName: string,
  matchingControlName: string
) {
  return (formGroup: FormGroup) => {
    let control = formGroup.controls[controlName];
    let matchingControl = formGroup.controls[matchingControlName];
    if (matchingControl.errors && !matchingControl.errors['confirmPasswordValidator']) {
      console.log(1);
      return;
    }
    if (control.value !== matchingControl.value) {
      console.log(2);
      matchingControl.setErrors({ confirmPasswordValidator: true });
    } else {
      console.log(3);
      matchingControl.setErrors(null);
    }
  };
}
