import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './racun.reducer';
import { IRacun } from 'app/shared/model/racun.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IRacunProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Racun extends React.Component<IRacunProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { racunList, match } = this.props;
    return (
      <div>
        <h2 id="racun-heading">
          Racuns
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create new Racun
          </Link>
        </h2>
        <div className="table-responsive">
          {racunList && racunList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Broj Racuna</th>
                  <th>Kupac</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {racunList.map((racun, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${racun.id}`} color="link" size="sm">
                        {racun.id}
                      </Button>
                    </td>
                    <td>{racun.brojRacuna}</td>
                    <td>{racun.kupac ? <Link to={`kupac/${racun.kupac.id}`}>{racun.kupac.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${racun.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${racun.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${racun.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Racuns found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ racun }: IRootState) => ({
  racunList: racun.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Racun);
