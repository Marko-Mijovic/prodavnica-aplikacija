import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './racun-stavke.reducer';
import { IRacunStavke } from 'app/shared/model/racun-stavke.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRacunStavkeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class RacunStavkeDetail extends React.Component<IRacunStavkeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { racunStavkeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            RacunStavke [<b>{racunStavkeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="redniBrojStavke">Redni Broj Stavke</span>
            </dt>
            <dd>{racunStavkeEntity.redniBrojStavke}</dd>
            <dt>
              <span id="kolicina">Kolicina</span>
            </dt>
            <dd>{racunStavkeEntity.kolicina}</dd>
            <dt>Artikal</dt>
            <dd>{racunStavkeEntity.artikal ? racunStavkeEntity.artikal.id : ''}</dd>
            <dt>Racun</dt>
            <dd>{racunStavkeEntity.racun ? racunStavkeEntity.racun.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/racun-stavke" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/racun-stavke/${racunStavkeEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ racunStavke }: IRootState) => ({
  racunStavkeEntity: racunStavke.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(RacunStavkeDetail);
