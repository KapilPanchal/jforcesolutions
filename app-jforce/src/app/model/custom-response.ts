import { Appuser } from "./appuser";

export interface CustomResponse {
    timestamp: Date;
    statusCode: number;
    status: string;
    message: string;
    developerMessage: string;
    data: {
        appUsers?: Appuser[], 
        appUser?: Appuser
    }
}
