/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityDetailsMySuffixDetailComponent } from 'app/entities/entity-details-my-suffix/entity-details-my-suffix-detail.component';
import { EntityDetailsMySuffix } from 'app/shared/model/entity-details-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityDetailsMySuffix Management Detail Component', () => {
        let comp: EntityDetailsMySuffixDetailComponent;
        let fixture: ComponentFixture<EntityDetailsMySuffixDetailComponent>;
        const route = ({ data: of({ entityDetails: new EntityDetailsMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityDetailsMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EntityDetailsMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityDetailsMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.entityDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
