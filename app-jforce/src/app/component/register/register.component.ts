import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { AppState } from 'src/app/model/app-state';
import { CustomResponse } from 'src/app/model/custom-response';
import { AppService } from 'src/app/service/app.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder, 
              private appService: AppService) { }

  ngOnInit(): void {
  }

  public appState$!: Observable<AppState<CustomResponse>>;

  public registrationForm = this.fb.group({
    username: [''],
    password: [''],
    emailId: [''],
    phoneNumber: [''],
    role: ['']
  });

  public onRegistrationFormSubmit($event: any): void  {
    
  }
}
