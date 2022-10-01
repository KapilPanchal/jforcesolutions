import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of, startWith } from 'rxjs';
import { AppState } from 'src/app/model/app-state';
import { CustomResponse } from 'src/app/model/custom-response';
import { Datastate } from 'src/app/model/enum/datastate'
import { AppService } from 'src/app/service/app.service';

@Component({
  selector: 'app-feeds',
  templateUrl: './feeds.component.html',
  styleUrls: ['./feeds.component.css']
})
export class FeedsComponent implements OnInit {

  appState$!: Observable<AppState<CustomResponse>>;
  readonly Datastate = Datastate;
  private dataSubject = new BehaviorSubject<CustomResponse | null>(null);

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    
    this.appState$ = this.appService.getAllUsers$
        .pipe(map(response => {
          this.dataSubject.next(response);
          return{
            dataState: Datastate.LOADED_STATE,
            appData: response
          }
        }),
        startWith({
          dataState: Datastate.LOADING_STATE
        }),
        catchError((err: CustomResponse)=> {
          return of ({
            dataState: Datastate.ERROR_STATE,
            error: err.message
          })
        })        
    );
  }
}

