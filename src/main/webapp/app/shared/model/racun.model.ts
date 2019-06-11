import { IKupac } from 'app/shared/model/kupac.model';
import { IRacunStavke } from 'app/shared/model/racun-stavke.model';

export interface IRacun {
  id?: number;
  brojRacuna?: string;
  kupac?: IKupac;
  racunStavkes?: IRacunStavke[];
}

export const defaultValue: Readonly<IRacun> = {};
