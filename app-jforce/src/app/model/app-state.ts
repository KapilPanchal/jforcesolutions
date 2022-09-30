import { Datastate } from "./enum/datastate";

export interface AppState<T> {
    dataState: Datastate;
    appState?: T;
    error?: string;    
}
