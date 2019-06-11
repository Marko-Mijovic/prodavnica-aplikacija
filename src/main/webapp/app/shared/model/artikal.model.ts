import { IRacunStavke } from 'app/shared/model/racun-stavke.model';

export interface IArtikal {
  id?: number;
  nazivArtikla?: string;
  cena?: number;
  racunStavkes?: IRacunStavke[];
}

export const defaultValue: Readonly<IArtikal> = {};
