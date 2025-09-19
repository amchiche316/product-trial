import { Component } from '@angular/core';
import {CommonModule} from '@angular/common'
import { ReactiveFormsModule, FormBuilder, Validators, FormControlName, FormGroup, FormControl } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [

    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    InputTextareaModule,
    ButtonModule,
    CardModule
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {

  form = this.fb.group(
    {
      email: new FormControl('', [Validators.required, Validators.email]),
      message: new FormControl('', [Validators.required, Validators.maxLength(300)])

    }
  );
  success = false;
  constructor(private fb:FormBuilder) {}



  get email() { return this.form.get('email')!;}
  get message() { return this.form.get('message')!;}

  onSubmit(){
    if (this.form.invalid){
      this.form.markAllAsTouched();
      return;
    }
    this.success = true;
    this.form.reset;
    setTimeout( () => this.success = false, 5000);
  }

}
