import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomResponse } from '../model/custom-response';

@Injectable({
  providedIn: 'root'
})
export class AppService{

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }
    
  getAllUsers$ = <Observable<CustomResponse>> 
  this.http.get<CustomResponse> (`${this.apiUrl}/api/get-users`)
  .pipe(tap(console.log),
        catchError(this.handleError));

  handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(() => new Error(`An Error ocurred - Code ${error.status}`));
  }
}
