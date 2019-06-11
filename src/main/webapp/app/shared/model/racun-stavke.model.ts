import { IArtikal } from 'app/shared/model/artikal.model';
import { IRacun } from 'app/shared/model/racun.model';

export interface IRacunStavke {
  id?: number;
  redniBrojStavke?: number;
  kolicina?: number;
  artikal?: IArtikal;
  racun?: IRacun;
}

export const defaultValue: Readonly<IRacunStavke> = {};
